#include "queue.h"
#include <string.h>
#include <stdio.h>


void init_queue(struct queue *q) {
    q->data = NULL;
    q->capacity = 0;
    q->offset = 0;
    q->length = 0;
}

void clear_queue(struct queue *q) {
    if (q->data != NULL) {
        free(q->data);
        init_queue(q);
    }
}

// Define a function to enqueue an element in the queue.
void enqueue(struct queue *q, int value) {
    // Check if the queue's capacity is 0 (empty queue).
    if (q->capacity == 0) {
        // Initialize the capacity to 8 and allocate memory for the data array.
        q->capacity = 8;
        q->data = malloc(sizeof(int) * q->capacity);
    }
        // Check if the queue is full (length is equal to capacity).
    else if (q->length == q->capacity) {
        // Double the capacity and reallocate memory for the data array.
        q->capacity *= 2;
        q->data = realloc(q->data, sizeof(int) * q->capacity);

        // Move existing elements in the data array to accommodate the new elements.
        memmove(q->data + q->length, q->data, sizeof(int) * q->capacity);
    }

    // Calculate the index where the new value will be added, considering the offset.
    q->data[(q->length + q->offset) % q->capacity] = value;

    // Increment the length of the queue to reflect the addition of the new element.
    (q->length)++;
}

// Define a function to dequeue an element from the queue; Updates the queue's capacity if it falls below a certain threshold.
bool dequeue(struct queue *q, int *out) {
    // Check if the queue is empty.
    if (q->length == 0)
        return false;  // If it's empty, return false (no elements to dequeue).

    // Store the value to be dequeued in the 'out' variable.
    *out = q->data[q->offset % q->capacity];

    // Update the offset and length of the queue.
    (q->offset)++;
    (q->length)--;

    // Check if the length of the queue is less than half of its capacity.
    if (q->length < q->capacity / 2) {
        // If the length is below the threshold, adjust the capacity and data array.

        // Reduce the capacity to half of its current value.
        q->capacity /= 2;

        // Move the elements in the data array to the beginning (offset 0).
        memmove(q->data, q->data + q->offset, sizeof(int) * q->length);

        // Reset the offset to 0.
        q->offset = 0;

        // Resize the data array to the updated capacity.
        q->data = realloc(q->data, sizeof(int) * q->capacity);
    }

    // Return true to indicate a successful dequeue operation.
    return true;
}


// Define a function to check if the queue is empty.
bool queue_empty(struct queue *q) {
    // Check if the length of the queue is equal to 0.
    if(q->length == 0)
        // If the length is 0, the queue is empty, so return true.
        return true;

    // If the length is not 0, the queue is not empty, so return false.
    return false;
}