# code-challenge

For ALL questions below, please send to us GitHub links containing your
proposed solutions.
For algorithm questions, please make sure is an actual solution (not
pseudo­code) and include (in text form) its corresponding complexity in time
and space. You can use any language of your choice.
Arrays and Strings

1. [Replacing characters in place:](https://github.com/derschatz/code-challenge/blob/master/algorithms/Algo1.java)


Given an array of characters, write a method to replace all the spaces with “&32”.
You may assume that the array has sufficient slots at the end to hold the additional
characters, and that you are given the “true” length of the array. (Please perform this
operation in place with no other auxiliary structure).
Example:
Input: “User is not allowed “, 19
Output: “User&32is&32not&32allowed”

2. [Check words with jumbled letters:](https://github.com/derschatz/code-challenge/blob/master/algorithms/Algo1.java)


Our brain can read texts even if letters are jumbled, like the following sentence: “Yuo
cna porbalby raed tihs esaliy desptie teh msispeillgns.” Given two strings, write a
method to decide if one is a partial­permutation of the other. Consider a
partial­permutation only if:
­ The first letter hasn’t changed place
­ If word has more than 3 letters, up to 2/3 of the letters have changed place
Examples:
you, yuo ­> true
probably, porbalby ­> true
despite, desptie ­> true
moon, nmoo ­> false
misspellings, mpeissngslli ­> false

[3. Check words with typos:](https://github.com/derschatz/code-challenge/blob/master/algorithms/Algo3.java)


There are three types of typos that can be performed on strings: insert a character,
remove a character, or replace a character. Given two strings, write a function to
check if they are one typo (or zero typos) away.
Examples:
pale, ple ­> true
pales, pale ­> true
pale, bale ­> true
pale, bake ­> false


[4. [Android] Search on a list:](https://github.com/derschatz/code-challenge/tree/master/application/searchapp)
Write an application with one activity that shows a list of items and a search box. The
user expects that the search returns a result even if word typed is partially permuted
or it has one typo (like explained on previous problems), but not both.
Linked Lists

[5. Remove duplicates on email thread:](https://github.com/derschatz/code-challenge/blob/master/algorithms/Algo5.java)
When different email clients are used on a same thread, the discussion get messy
because old messages are included again and get duplicated. Given a email thread
(represented by a singly unsorted linked list of messages), write a function that
remove duplicated messages from it.

[6. [Android] Email processor service:](https://github.com/derschatz/code-challenge/tree/master/application/EmailProcessorService)
Write an application with one service (no activities) that receive requests from other
apps with an email thread (as a linked­list), applies the previous algorithm and
returns a cleaner version of same email thread. Concurrent requests should be
queued.

[7. Linked List Intersection:](https://github.com/derschatz/code-challenge/blob/master/algorithms/Algo7.java)

If two requests on the queue have linked lists that intersect (like the example below),
previous service could be improved to process only the difference between them.
Write a method that receives two singly linked lists and return the intersecting node
of the two lists (if exists). Note that the intersection is defined by reference, not value.
(No need to change previous answer).
