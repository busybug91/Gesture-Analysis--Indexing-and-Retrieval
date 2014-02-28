function [U,S,Vt]= calSVD(input,k)
input;
[U,S,V]=svds(input,k);
Vt=transpose(V);
end
