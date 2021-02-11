#!/usr/bin/env python
# coding: utf-8

# In[15]:


import numpy as np
from geneticalgorithm import geneticalgorithm as ga
from datetime import datetime

# Defining variables: number of queens, chess_board(this is the main board), size of the grid, queen_positions.
n_queens = 8
grid_size = n_queens*n_queens


# In[8]:


# find how many queens are attacking each other
def fitnessfunction(seq):
    i=0
    arr = []
    for x in seq:
        arr.append([i,x])
        i+=1
    # starts here
    penalty = 0
    for i in range(len(arr)):
        for j in range(i+1,len(arr)):
            k1 = abs(arr[i][0] - arr[j][0])
            k2 = abs(arr[i][1] - arr[j][1])
            if k1 == k2:
                penalty +=1
            if arr[i][1] == arr[j][1]:
                penalty +=1
        
    return penalty

"""
0,1
1,2
2,3
3,4
4,5
5,1
6,0
7,1

"""


# In[16]:


varbound=np.array([[0,7]]*n_queens)
algorithm_param = {'max_num_iteration': None,                   'population_size':100,                   'mutation_probability':0.2,                   'elit_ratio': 0.01,                   'crossover_probability': 0.5,                   'parents_portion': 0.3,                   'crossover_type':'uniform',                   'max_iteration_without_improv':None}
model = ga(
    function = fitnessfunction,
    dimension = n_queens,
    variable_type = "int",
    variable_boundaries = varbound,
    algorithm_parameters = algorithm_param
)
start = datetime.now()

model.run()
print(datetime.now() - start)
