function [COEFF,latent,explained]= calPCACOV(input,k)
input
[COEFF,latent,explained]=pcacov(input)
TopThree=COEFF(:,1:k)

end
