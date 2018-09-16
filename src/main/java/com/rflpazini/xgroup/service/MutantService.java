package com.rflpazini.xgroup.service;

import com.rflpazini.xgroup.model.Mutant;
import com.rflpazini.xgroup.repository.MutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class MutantService {
    Logger logger = Logger.getLogger(MutantService.class.getName());

    @Autowired
    MutantRepository mutantRepository;

    public List<Mutant> getMutants() {
        return mutantRepository.findAll();
    }

    public boolean isMutant(Mutant mutant) {
        Mutant checkExistentMutant = saveRegistry(mutant);
        if (checkExistentMutant != null) {
            return checkExistentMutant.getType() == 1 ? true : false;
        }

        String[][] dnaSequence = convertDnaListToArray(mutant.getDna());
        int  mutantStatus = searchMutantGenes(dnaSequence) ? 1 : 0;
        mutant.setType(mutantStatus);

        if (mutantStatus == 1) {
            return true;
        }

        return false;
    }

    private Mutant saveRegistry(Mutant mutant) {
        Mutant registry = checkIfDnaWasStoredOnDb(mutant);
        if (registry == null) {
          mutantRepository.save(mutant);
          return null;
        }

        return registry;
    }

    private Mutant checkIfDnaWasStoredOnDb(Mutant mutant) {
        Mutant newMutant =  mutantRepository.findMutantByDna(
                mutant.getDna().stream()
                .collect(Collectors.joining(","))
        );

        if (newMutant != null) {
            return newMutant;
        }
        return null;
    }

    private String[][] convertDnaListToArray(List<String> dna) {
        int rowSize = dna.size();
        String[] dnaArray = new String[rowSize];
        dnaArray = dna.toArray(dnaArray);

        return convertDnaArrayTo2DArray(dnaArray);
    }

    private String[][] convertDnaArrayTo2DArray(String[] dnaArray) {
        int n = dnaArray.length;
        String[][] dnaSequence = new String[n][n];

        for (int j = 0; j < n; j++) {
            String[] currentLine = dnaArray[j].split("");
            for (int i = 0; i < currentLine.length; i++) {
                dnaSequence[j][i] = currentLine[i];
            }
        }

        return dnaSequence;
    }

    private boolean searchMutantGenes(String[][] dnaSequence) {
        int maxX = dnaSequence[0].length;
        int maxY = dnaSequence.length;
        int count = 0;

        int[][] allDirections = {{1, 0}, {1, -1}, {1, 1}, {0, 1}};
        for (int[] direction : allDirections) {
            int directionX = direction[0];
            int directionY = direction[1];
            for (int x = 0; x < maxX; x++) {
                for (int y = 0; y < maxY; y++) {
                    int lastX = x + 3 * directionX;
                    int lastY = y + 3 * directionY;
                    if (0 <= lastX && lastX < maxX && 0 <= lastY && lastY < maxY) {
                        String genes = dnaSequence[x][y];
                        if (!genes.isEmpty() && genes.equals(dnaSequence[x + directionX][y + directionY])
                                && genes.equals(dnaSequence[x + 2 * directionX][y + 2 * directionY])
                                && genes.equals(dnaSequence[lastX][lastY])) {
                            count++;
                            System.out.println("[MUTANT_GENES]: " + genes);
                        }
                    }
                }
            }
        }

        if (count > 1) {
            return true;
        }
        return false;
    }
}
