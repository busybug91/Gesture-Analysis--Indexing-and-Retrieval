function [w,bias]=svmCalDual(x,y)
% x=[3 7;4 6;5 6;7 7;8 5;4 5;5 5;6 3;7 4;9 4]
% y=[1;1;1;1;1;-1;-1;-1;-1;-1]
g=x*x'
n=size(x,2)
ell=size(x,1)
h=(y*y').*(x*x')
f=-ones(1,ell)
A=zeros(1,ell)
c=0

Aeq=y'
ceq= 0

LB=zeros(ell,1)

UB=inf*ones(ell,1)

alpha= quadprog(h,f,A,c,Aeq,ceq,LB,UB)

UB=1000*ones(ell,1)
alpha= quadprog(h,f,A,c,Aeq,ceq,LB,UB)

w=x'*(alpha.*y)
i=min(find((alpha>0.1) & (y==1)))
bias=1-g(i,:)*(alpha.*y)
size(bias)
size(ones(ell,1))
F=g*(alpha.*y)+bias*ones(ell,1)
