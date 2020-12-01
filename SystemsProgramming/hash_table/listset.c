#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

// include the header files with the declarations of listset
#include "listset.h"

// create a new, empty linked list set
struct listset * listset_new() {
  struct listset *newSet = malloc(sizeof(struct listset));
  newSet->head = malloc(sizeof(struct listnode));
  newSet->head = NULL;
  return newSet;
}

/* check to see if an item is in the set
   returns 1 if in the set, 0 if not */
int listset_lookup(struct listset * this, char * item) {
  struct listnode * iter = this->head;
  while(iter != NULL) {
    if(strcmp(iter->str, item)) {
      return 1;
      iter = iter->next;
    }
  }
  return 0;
}

// add an item, with number 'item' to the set
// has no effect if the item is already in the set.
// New items that are not already in the set should
// be added to the start of the list
void listset_add(struct listset * this, char * item) {
  if (listset_lookup(this, item) == 0) {
    struct listnode *newNode = malloc(sizeof(struct listnode));
    newNode->str = item;
    newNode->next = this->head;
    this->head = newNode;
  }
}

// remove an item with number 'item' from the set
void listset_remove(struct listset * this, char * item) {
  if (listset_lookup(this, item) == 1) {
    struct listnode * iter = this->head;
    while(iter != NULL) {
      if(iter->next != NULL) {
        if(strcmp(iter->next->str,item)) {
          iter->next = iter->next->next;
          return;
        }
      }
      iter = iter->next;
    }
  }
}
  
// place the union of src1 and src2 into dest
void listset_union(struct listset * dest, struct listset * src1, struct listset * src2) {
  for (struct listnode *iter = src1->head; iter != NULL; iter = iter->next) {
    listset_add(dest, iter->str);
   }
  for (struct listnode *iter = src2->head; iter != NULL; iter = iter->next) {
    listset_add(dest, iter->str);
  }
}

// place the intersection of src1 and src2 into dest
void listset_intersect(struct listset * dest, struct listset * src1, struct listset * src2) {
  for (struct listnode *iter = src1->head; iter != NULL; iter = iter->next) {
    if(listset_lookup(src2, iter->str) == 1) {
      listset_add(dest, iter->str);
    }
   }
}

// return the number of items in the listset
int listset_cardinality(struct listset * this) {
  int count = 0;
  for (struct listnode *iter = this->head; iter != NULL; iter = iter->next) {
    count++;
  }
  return count;
}

// print the elements of the list set
void listset_print(struct listset * this) {
  struct listnode * p;

  for ( p = this->head; p != NULL; p = p->next ) {
    printf("%s, ", p->str);
  }
  printf("\n");
}
