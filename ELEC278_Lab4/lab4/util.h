#ifndef LAB4_UTIL_H
#define LAB4_UTIL_H

#include <stdbool.h>

// Checks whether 'str' starts with 'prefix'.
bool starts_with(const char *str, const char *prefix);

// Advances '*input' past any whitespace.
void skip_whitespace(const char **input);

bool peek_keyword(const char *input, const char *keyword);
#endif //LAB4_UTIL_H