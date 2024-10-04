#!/bin/bash

# Function to delete dangling images
delete_dangling_images() {
    # Find and delete dangling images
    dangling_images=$(docker images -f "dangling=true" -q)

    if [ -n "$dangling_images" ]; then
        echo "Deleting dangling images..."
        docker rmi -f $dangling_images
        echo "Dangling images deleted."
    else
        echo "No dangling images found."
    fi
}

# Run the function
delete_dangling_images
