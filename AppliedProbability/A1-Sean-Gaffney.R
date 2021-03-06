#By generating 10,000 pairs of samples, find the probability that 2 randomly chosen integers are
#relatively prime (coprime). You are required to calculate the mean of results for 200 iterations.

numPairs = 10000
iterations = 200
sums <- replicate(iterations, sum(replicate(numPairs, coprime(sample(1:numPairs,1), sample(1:numPairs,1)))/10000))
result <- mean(sums)
