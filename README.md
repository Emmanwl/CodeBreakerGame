Code breaking is all about finding the secret code.

When the game starts the player should be able to guess the secret code  by providing a 4 digits number. The game finishes when the player have found the exact match for the secret code.


- The game will return a + sign for an exact match
- The game will return a - sign for a digit match
- An exact match is a digit that matches a digit of the secret code both in value and in position
- A digit match is a digit that matches a digit of the secret code in value but does not have the correct position
- Exact matches have priority over digit matches
- Once a digit has been used for an exact match in the secret code, it can no longer be used for any digit match

Examples:

Secret: 1234, Proposal: 1245 , Response: ++- because two exact matches (1,2) and one digit match (4)</br>
Secret: 1234, Proposal: 2002 , Response: -</br> 
Secret: 1234, Proposal: 2200 , Response: + because exact matches have priority over digit matches (rule 5)</br>
Secret: 1234, Proposal: 1234 , Response: ++++</br>
Secret: 2234, Proposal: 2234 , Response: ++++</br>
