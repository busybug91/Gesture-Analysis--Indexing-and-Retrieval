function svmClassify(x,y)

% x=[3 7;4 6;5 6;7 7;8 5;4 5;5 5;6 3;7 4;9 4]
% y=[1;1;1;1;1;-1;-1;-1;-1;-1]
svmStruct=svmtrain(x,y)
% svmclassify(svmStruct)