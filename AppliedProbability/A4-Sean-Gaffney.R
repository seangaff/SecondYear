iterations = 200
samples = 10000
#Q1
#The number of customers attending a shop in (0,t) has Poisson distribution
#with parameter βt. The time that each customer spends in the shop has Exponential
#distribution with parameter . Find the probability that while a particular
#customer is shopping, maximum k other customer arrive. Use (any) β=λ and k=2.
#Use 10,000 samples.
k = 2
t = 0
lambda =
rpois(10000, lambda)
rexp(10000, 1/lambda)

#Q2
#Assume X~U(0, a) and Y= {X       X<a2 a2       X≥a2  .
#For each value of a=1, …, 5, and using 10,000 random samples of X, find E[Y].
#Plot you answer for EY verses a .
a = c(1:5)


plot(a, expected)