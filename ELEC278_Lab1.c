#include <stdio.h>

float array_average(const int *array, size_t count) {
    // TODO: return the average of the 'count' elements of 'array'.
  
    int sum = 0;
    float avg = 0;

    for (int i = 0; i < count; i++){
        sum = sum + array[i];
    }
    avg = sum/count;
    return avg;
}

int maxElement(const int *array, size_t count){
    // largest value
    int max = 0;

    for(count = 0; count < 10; count++){
        if (array[count] > max){
            max = array[count];
        }
    }
    return max;
}

int main() {
    // TODO: construct an array, call 'array_average' on it, and print the result.

    int listNumber[10] = {1,5,3, 7, 12, 2, 34, 876, 32, 74};

    int size = 10;

    printf("%0.2f is the array average.\n", array_average(listNumber, size));

    printf("%d is the max element of the array.", maxElement(listNumber, size));


    return 0;
}
