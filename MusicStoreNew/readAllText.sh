#!/bin/bash

# Function to recursively explore directory and concatenate text content
explore_directory() {
  local dir="$1"
  local content=""

  for item in "$dir"/*; do
    if [ -f "$item" ]; then
      # Concatenate the content of the file
      content+=$(cat "$item")
      content+="\n"
    elif [ -d "$item" ]; then
      # Recursively explore the directory
      content+=$(explore_directory "$item")
    fi
  done

  echo "$content"
}

# Check if directory argument is provided
if [ -z "$1" ]; then
  echo "Usage: $0 <relative-directory>"
  exit 1
fi

# Get the relative directory from arguments
relative_directory="$1"

# Call the function and store the result
result=$(explore_directory "$relative_directory")

# Print the concatenated text content
echo -e "$result"
