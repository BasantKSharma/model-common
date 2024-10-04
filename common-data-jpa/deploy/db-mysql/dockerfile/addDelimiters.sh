#!/bin/sh
# This script adds DELIMITER lines to the top and bottom of a given SQL file
file="$1"
temp_file="${file}.temp"

echo "DELIMITER \$$" > "$temp_file"
cat "$file" >> "$temp_file"
echo "DELIMITER \$$" >> "$temp_file"
mv "$temp_file" "$file"
