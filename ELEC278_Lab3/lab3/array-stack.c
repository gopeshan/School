#include <malloc.h>
#include "stack.h"

struct stack {
    // Points to dynamically allocated array of size 'capacity', or is NULL.
    char *data;
    // The capacity of the array.
    size_t capacity;
    // The number of entries currently in the stack.
    size_t length;
};

stack_ptr stack_new() {
    stack_ptr s = malloc(sizeof(struct stack));
    s->data = NULL;
    s->capacity = 0;
    s->length = 0;
    return s;
}

void stack_free(stack_ptr s) {
    if (s->data != NULL)
        free(s->data);
    free(s);
}

void stack_push(stack_ptr s, char c) {
    // TODO (task 2): how do we push an entry onto the stack?
    // Check if the stack data array is NULL
    if (s->data == NULL) {
        // Allocate memory for the stack data array with an initial size of one element
        s->data = (char *) malloc(sizeof(struct stack));
        if (s->data == NULL) {
            // Handle memory allocation failure (missing in this code)
        }
    }

    // Increment the length and add character 'c' to the end of the stack data array
    s->data[++s->length] = c;
}

bool stack_pop(stack_ptr s, char *out) {
    // TODO (task 2): how do we pop an entry from the stack?
    // Check if the stack is empty (length is zero)
    if (s->length == 0)
        return false; // Return 'false' to indicate an empty stack

    // Get the character at the end of the stack data array and decrement the length
    *out = s->data[s->length--];

    // Return 'true' to indicate a successful pop operation
    return true;
}
