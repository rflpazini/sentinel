#!/bin/sh
FILES=$(git diff --cached --name-only --diff-filter=ACM "*.java" | sed 's| |\\ |g')
[ -z "$FILES" ] && exit 0

# Prettify all selected files
echo "$FILES" | xargs ./gradlew goJF -DgoogleJavaFormat.include=\"$FILES\" --daemon

# Add back the modified/prettified files to staging
echo "$FILES" | xargs git add

exit 0