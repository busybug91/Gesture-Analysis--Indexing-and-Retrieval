function [w,bias]=svmCal(x,y)
% x=[1 6;1 10;4 11;5 2;7 6;10 4]
% y=[1;1;1;-1;-1;-1]
n=size(x,2)
ell=size(x,1)
H=diag([ones(1,n) 0])
f=zeros(1,n+1)
A=-diag(y)*[x ones(ell,1)]
c=-ones(ell,1)
z=quadprog(H,f,A,c)
w=z(1:n)
bias=z(n+1)
x*w+bias*ones(ell,1)
