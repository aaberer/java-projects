'''
     PA2: Boolean functions and truth-table inputs
     -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-

For PA2 you will implememnt a number of Boolean functions,
such as implies, nand, etc.

a) Boolean functions
You are provided with a set of Boolean functions to implement.
You will create the bodies for these 2 parameter functions, such that 
they return the appropriate Boolean value (True or False). 
The formal parameters are always p and q (in that order)

Notice the difference between npIMPnq and nqIMPnp:
In npIMPnq you return not p implies not q (not first 
param implies not second param), for example 
  npIMPnq(True,False) returns True.

In nqIMPnp you return not q implies not p (not second 
param implies not first param), for example
  nqIMPnp(True,False) returns False.

b) Truth-table inputs
The function make_tt_ins(n) will create all combinations of n Boolean variables. 
The output is in the form of a list, each element of which is a list with n
Boolean values.
For example, make_tt_ins(2) should return
[[False, False], [False, True], [True, False], [True, True]]

Two functions are provided: run(f) and main, so that you can test 
your code.

  python3 PA2.py <fName> tests your Boolean function <fName>
  python3 PA2.p=y tt <n> tests your function make_tt_ins(<n>)
 
'''


import sys

# p implies q
def implies(p, q):
    return not(p) or q

# not p implies not q
def npIMPnq(p,q):
    return not(not(p) and q)

# not q implies not p
def nqIMPnp(p,q):
    return not(not(q) and p)

# p if and only if q
# equivalent to:  (p implies q) and (q implies p)
def iff(p, q):
    return (not(p) or q) and (not(q) or p)

# not ( p and q )
def nand(p, q):
    return not(p and q)

# not p and not q
def npANDnq(p,q):
    return not(p) and not(q)

# not ( p or q)
def nor(p, q):
    return not(p or q)

# not p or not q
def npORnq(p,q):
    return not(p) or not(q)

# The function make_tt_ins(n) will create all combination of Boolean values for n variables, 
# which is the input that needs to be provided for a truth table with n variables.
def make_tt_ins(n):
    if(n == 0):
        return [[]]
    else:
        return [row + [combo] for row in make_tt_ins(n-1) for combo in [True, False]]


#provided
def run(f):
  print("  True,True  : ", f(True,True)) 
  print("  True,False : ", f(True,False))  
  print("  False,True : ", f(False,True))
  print("  False,False: ", f(False,False))
  print()
      
#provided
if __name__ == "__main__":
  print("program", sys.argv[0])
  f1 = sys.argv[1]
  print(f1)
  if(f1 == "implies"):
    run(implies)
  if(f1 == "iff"):
    run(iff)
  if(f1 == "npIMPnq"):
    run(npIMPnq)
  if(f1 == "nqIMPnp"):
    run(nqIMPnp)
  if(f1 == "nand"):
    run(nand)
  if(f1 == "nor"):
    run(nor)
  if(f1 == "npANDnq"):
    run(npANDnq)
  if(f1 == "npORnq"):
    run(npORnq)
  if(f1 == "tt"):
    print(make_tt_ins(int(sys.argv[2])))
    

