Feature: Guess Word
  As a player
  I want to guess a 5,6,7 letter word
  So I can win the round and earn points
Scenario: The word has been guessed
  Given Its my turn
  When I guess a word and its correct
  Then The score should be updated with the according formula And a new round should be started
Scenario: The letter exists in word
  Given I am guessing a word
  When I guess a word and the letter exists in word
  Then That letter should be marked as CORRECT
Scenario: The word is incorrect
  Given Its my turn
  When I guess a word and its incorrect
  Then Advice should be given to the player about the word that should be guessed And the letter should be marked as INVALID and the next turn starts
Scenario: The word is incorrect
  Given Its my turn
  When I guess a word and its incorrect And its at the end of the round
  Then A new round should be started
Feature: Start new round
  As a player
  I want to start a new round
  So I can guess a new word
Scenario: There is already a game going on
  Given Im at the start of the game
  When I want to start a game but there's already a game going on
  Then Create a new instance of the game anyways and let the player have multiple games at the same time
Feature: See advice after turn
  As a player
  I want to see the corresponding advice after every turn
  So I can guess the word better
Scenario: Letter exists in word but is not at the right index
  Given I am guessing a word
  When Letter exists in word but is not at the right index
  Then Mark the letter as PRESENT
Scenario: Word doesnt exist in table
  Given I am guessing a word
  When The database table doesnt contain the word
  Then Return all entries as INVALID and start next turn
Scenario: Word is not the right size
  Given I am guessing a word
  When The word is not the right size for the to be guessed word
  Then Return all entries as INVALID and start next turn
Feature: Pause round
  As a player
  I want to pause the round
  So I can continue the game later
#<word>,<guess> en <feedback>
Scenario Outline: Guessing a word
  Given I am guessing a word with word "<word>"
  When The word is "<guess>"
  Then  Mark the according entries as "<feedback>"
  Examples:
    | word | guess | feedback |
    | aagje           | aagje           | CORRECT |
    | acryl           | actie           | PRESENT |
    | actie1235       | bergen          | INVALID |

