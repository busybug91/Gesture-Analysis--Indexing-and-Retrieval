function [COEFF,latent,explained,TopK]= calPCACOV(covInput,k)
[COEFF,latent,explained]=pcacov(covInput);
TopK=COEFF(:,1:k);
end
