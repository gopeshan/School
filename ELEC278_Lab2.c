#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

struct list_node {
    int value;
    struct list_node* next;
};

void print_list(struct list_node *head) {
// TODO: implement this function for printing all nodes(task 1).
  
    while (head != NULL){
        printf("%d", head->value);
        if (head->next != NULL){
            printf(", ");
        } else{
            printf("\n");
        }
        head = head->next;
    }
}

struct list_node* insertFirst(struct list_node *head, int data) {
// TODO: implement this function for inserting the first node in an empty linked list(task 1).
  
    struct list_node* new_node = (struct list_node*)malloc(sizeof (struct list_node));
    new_node->value=data;
    new_node->next=NULL;

    head = new_node;
    return head;
}

void insert_at_tail(struct list_node *head, int value) {
// TODO: implement this function for inserting a node at the tail of the linked list  (task 1).
  
    struct list_node* new_node = (struct list_node*) malloc(sizeof (struct list_node));
    new_node->value=value;
    new_node->next = NULL;

    if (head == NULL){
        head = new_node;
    } else{
        struct list_node* current = head;
        while (current-> next != NULL){
            current = current-> next;
        }
        current->next = new_node;
    }
}


void swap_adjacent(struct list_node *head) {
    // TODO: implement this (task 2).
  
    int temp;
    struct list_node *point;
    point = head;
    while (point->next != NULL){
        temp = point->value;
        point->value = point->next->value;
        point->next->value = temp;

        if (point->next->next != NULL){
            point = point->next->next;
        } else {
            break;
        }
    }
}

void double_list(struct list_node *head) {
    // TODO: implement this (task 3).

    struct list_node *point = head;
    struct list_node *new = NULL;
    struct list_node *last = head;


    new = insertFirst(new, point->value);

    while(point->next != NULL){
        insert_at_tail(new, point->next->value);
        point = point->next;
    }

    while((last->next) != NULL){
        last = last->next;
    }
    last->next = new;
}

void remove_adjacent_duplicates(struct list_node *head) {
    // TODO: implement this (task 4).
  
    struct list_node *point = head;
    while (point->next != NULL) {
        if (point->value == point->next->value) {
            point->next = point->next->next;
        }
        point = point->next;
    }
}

    int main() {

        struct list_node *head = NULL;

        head = insertFirst(head, 1);
        insert_at_tail(head, 2);
        insert_at_tail(head, 3);
        insert_at_tail(head, 3);
        insert_at_tail(head, 4);
        insert_at_tail(head, 5);
        insert_at_tail(head, 6);


        printf("Original list: ");
        print_list(head);

        printf("\nSwapping adjacent items: ");
        swap_adjacent(head);
        print_list(head);

        printf("\nDuplicating all items: ");
        double_list(head);
        print_list(head);

        printf("\nRemoving adjacent duplicates: ");
        remove_adjacent_duplicates(head);
        print_list(head);

        return 0;
    }
