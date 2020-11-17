#include "bitset.h"

// create a new, empty bit vector set with a universe of 'size' items
struct bitset * bitset_new(int size) {
    struct bitset * temp;
	temp = malloc(sizeof(struct bitset));
	int size_in_words = ((size - 1)/64) + 1;
	uint64_t * bits = malloc(sizeof(uint64_t*)*size_in_words);
	for (int i = 0; i < size_in_words; i++) {
		bits[i] = 0;
	}
	temp -> bits = bits;
	temp -> size_in_words = size_in_words;
	temp -> universe_size = size_in_words*64;
	return temp;
}

// get the size of the universe of items that could be stored in the set
int bitset_size(struct bitset * this) {
    int universeSize = this -> universe_size;
    return universeSize;
}

// get the number of items that are stored in the set
int bitset_cardinality(struct bitset * this){
    int count = 0;
    for (int i = 0; i < this -> size_in_words; i++) {
        uint64_t item = this -> bits[i];
        while(item != 0) {
            if(item % 2 == 1) {
                count++;
            }
            item = item/2;
        }
    }
    return count;
}

// check to see if an item is in the set
int bitset_lookup(struct bitset * this, int item){
    int block = item / 64;
    int index = item % 64;
    uint64_t word = this -> bits[block];
    uint64_t mask = 1ULL << 63;
    uint64_t result = word << index;
    if (result & mask) {
        return 1;
    }
    else {
        return 0;
    }
}

// add an item, with number 'item' to the set
// has no effect if the item is already in the set
void bitset_add(struct bitset * this, int item) {
    int block = item / 64;
	int index = item % 64;
	uint64_t word = this -> bits[block];
	uint64_t mask = 1UL << (63 - index);
	uint64_t result = word | mask;
	this -> bits[block] = result;
}

// remove an item with number 'item' from the set
void bitset_remove(struct bitset * this, int item) {
    int block = item / 64;
	int index = item % 64;
	uint64_t word = this -> bits[block];
	uint64_t mask = ~0UL;
	mask = mask ^ (1UL << (63 -index));
	this -> bits[block] = word & mask;
}

// place the union of src1 and src2 into dest;
// all of src1, src2, and dest must have the same size universe
void bitset_union(struct bitset * dest, struct bitset * src1, struct bitset * src2) {
    int size = bitset_size(src1);
	for (int i = 0; i < size; i++) {
		if (bitset_lookup(src1, i) == 1 || bitset_lookup(src2, i) == 1) {
			bitset_add(dest, i);
		} else {
			bitset_remove(dest, i);
		}
	}
}

// place the intersection of src1 and src2 into dest
// all of src1, src2, and dest must have the same size universe
void bitset_intersect(struct bitset * dest, struct bitset * src1, struct bitset * src2) {
    int size = bitset_size(src1);
	for (int i = 0; i < size; i++) {
		if (bitset_lookup(src1, i) == 1 && bitset_lookup(src2, i) == 1) {
			bitset_add(dest, i);
		} else {
			bitset_remove(dest, i);
		}
	}
}
