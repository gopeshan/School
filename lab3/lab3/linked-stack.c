#include <malloc.h>
#include "stack.h"

struct stack_node {
    // TODO (task 1): what fields do we need here?

    // holds character value in this node
    char data;

    // pointer to next node in the stack in linked list
    struct stack_node* next;
};

struct stack {
    // Null if the stack is empty.
    struct stack_node *head;
};

stack_ptr stack_new() {
    stack_ptr s = malloc(sizeof(struct stack));
    s->head = NULL;
    return s;
}

void stack_free(stack_ptr s) {
    // TODO (task 1): how do we make sure the nodes don't leak memory?
    while (s->head != NULL){

        // store a pointer to the current head node
        struct stack_node *temp = s->head;

        // update the head pointer to point to the next node
        s->head = s->head->next;

        // free memory of the removed node
        free(temp);
    }

    // free memory of the stack structure itself
    free(s);
}

void stack_push(stack_ptr s, char c) {
    // TODO (task 1): how do we push an entry onto the stack?

    // create new node and set its data and next pointer
    struct stack_node *new = (struct stack_node *) malloc(sizeof(struct stack_node));

    // store the character 'c' in the new node's data
    new->data = c;

    // set new node's next pointer to the current head of the stack
    new->next = s->head;

    // update the head pointer to the new node making it the new top of the stack
    s->head = new;
}

bool stack_pop(stack_ptr s, char *out) {
    // TODO (task 1): how do we pop an entry from the stack?
    // Check if the stack is empty
    if (s->head == NULL)
        return false;

    // Store the data of the current head node in 'out'
    struct stack_node *temp = s->head;
    *out = temp->data;

    // Update the head pointer to point to the next node, effectively removing the current head node
    s->head = (s->head)->next;

    // Free the memory of the removed node
    free(temp);

    // Return 'true' to indicate a successful pop operation
    return true;
}