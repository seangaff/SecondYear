iterations = 200
samples = 10000
#Q1
#Assume that the lifetime Ti of the lamps made by factory A follows an Exponential
#distribution with average of 24000 hours. Assume we have k of these lamps, and
#therefore the total time that we can use them would be equal to T= T1+T2+…+ Tk .
#The percentage of the time that we would use the first lamp is therefore
#presented as p=100T1T%. 
#For values of k=2, …, 5 and using 10,000 random samples lamps, estimate the
#percentage defined above and plot the results verses k.
numLamps = c(2:5)
percentages <- vector()
percentChance = function(numLamps){
  samples = rexp(samples, 1/24000)
  timings = sample(samples, k)
  sum = sum(timings)
  return(100*(timings[1]/sum) )
}
for(k in numLamps) {
  percents = replicate(iterations, percent(k))
  mean(percents)
  percentages = c(percentages, mean(percents))
}
plot(numLamps, percentages)

#Q2
#By generation 10,000 samples from random variable X~ Nμ, 2, find the value of
 #2 for which P(a<X<b) is maximum. Assume μ=0, a=1, b=2. 
u = 0
a = 1
b = 2
possible <- c(1:200)
findP = function(v) {
  values <- rnorm(10000,u,v)
  count = 0;
  for(x in values) {
    if(x < b && x > a) {
      count = count + 1
    }
  }
  return(count) 
}
max = 0
result = 0
for(x in possible) {
  if(findP(x) > max) {
    max = findP(x)
    result = x
  }
}
result = result^2
