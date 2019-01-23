#!/bin/sh
FILES=$(git diff --cached --name-only --diff-filter=ACM | paste -s -d",")
[ -z "$FILES" ] && exit 0

# Prettify all selected files
echo "$FILES" | xargs ./gradlew goJF -DgoogleJavaFormat.include=\"$FILES\" --daemon

# Add back the modified/prettified files to staging
echo "$FILES" | xargs git add

exit 0


#
##!/bin/sh
##
## An example hook script to verify proper formatting.
#
#files=$(git diff --cached --name-only --diff-filter=ACM | paste -s -d",")
## this only works if ignoreFailures is set to false (which it is by default)
#./gradlew verifyGJF -DverifyGoogleJavaFormat.include="$files" &>/dev/null && exit 0
#
#echo "Some files are not formatted properly. Please run:"
#echo "./gradlew gJF -DgoogleJavaFormat.include=\"$files\""
#exit 1