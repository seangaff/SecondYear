 iterations = 10000

# Q1
#
# Using 10,000 random samples, estimate the number of required dice rolling to get each face at least
#once

roll = function() return(sample(1:6, iterations, rep=T))
'%!in%' <- function(x,y)!('%in%'(x,y))
getRollsForSet <- function() {
  found <- c()
  sampleRoll <- c()
  sampleRoll <- roll()
  numRolls = 0
  while(length(found) < 6 && numRolls < length(sampleRoll)){
    if(sampleRoll[numRolls+1] %!in% found) found <- c(found, sampleRoll[numRolls + 1])
    numRolls = numRolls + 1
  }
  return(numRolls)
}
getRollsForSet()
randomRolls <- replicate(200, getRollsForSet())
result <- mean(randomRolls)

# Q2
#
#Assume that k cards, numbered from 1 to k, are place on a desk, faced down. You are going to guess
#the numbers on the cards. You would randomly guess the cards by saying numbers between 1 to k.
#Obviously, you would use each number once to guess. Therefore, a guess such 2,2, …, 2 is not
#acceptable!
#For k=6, 7, 8, 9, 10, and using 10,000 random samples, estimate the probability that none of
#the numbers that you guess are correct. Plot the result of probabilities vs. k.

guess <- function() return(sample(cards))
makeGuesses <- function(k) {
  cards <- letters[1:k]
  allGuess = replicate(iterations, length(which(cards == sample(cards))))
  count <- length(which(allGuess==0))
  p <- count/iterations
  return(p)
}
values <- c(6:10)
probs <- vector()
for(k in values) {
  probs<- c(probs, mean(replicate(200, makeGuesses(k))))
}
plot(probs, values)

