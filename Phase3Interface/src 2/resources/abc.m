input=[1,1,2,3,1,2,3,4;1,1,2,3,5,6,4,8;1,1,2,3,2,4,7,9;2,3,4,3,3,2,7,4];
disp('New')
covInput=calCOV(input)
[COEFF,latent,explained]=calPCACOV(covInput,3);
% [U,S,Vt]=calSVD(input,3);
