'''
          PA3: Truth tables and equivalence
          -_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-

For PA3 you will be provided with a function callf2(f,p,q) 
which will cal f(p,q), and with a main to test your code. 
You will implement two functions: eval_tt_f2(f) and
equivalent(tt1,tt2). 

The function eval_tt_f2(f) will, given a two argument Boolean 
function f ( one of the functions you created in PA2.py), return 
the truth table for f. Use your make_tt_ins(n) function from 
PA2.py to create an arrayList with n inputs, then use callf2 
to append the truth value for f to each row of that arrayList. 
For example, eval_tt_f2(iff) will return:

[[False, False, True], [False, True, False], [True, False, False], [True, True, True]]

The function equivalent(tt1,tt2) will return True if tt1 and tt2 
are equivalent and False otherwise. For example, 
  equivalent(eval_tt_f2(PA2.implies), eval_tt_f2(PA2.nqIMPnp))  
will return True.

You will need to add your code from PA2 by selecting PA2.py as the current file above.

'''

import sys
import PA2

# provided
def callf2(f, p, q):
  return f(p,q)

# implement this
def eval_tt_f2(f):
  inputs = PA2.make_tt_ins(2)
  rtr = []
  for input_combination in inputs:
    p, q = input_combination
    call = callf2(f, p, q)
    rtr.append([p, q, call])
  return rtr

# implement this
def equivalent(tt1,tt2):
  if tt1 == tt2:
    return True
  else:
    return False

# implement this
def is_tautology(tt):
  return all(row[-1] for row in tt)
    
# use program input section to input choice
# ex. entering 'iff' will run code after the label # one arg
# and entering 'iff implies' will run code after the labe # one arg and # two args
if __name__ == "__main__":
  print("program", sys.argv[0])
  args = input().split()
  argc = len(args)
  f1 = args[0]
  print(f1)
  tt1 = []
  # one arg
  if(f1 == "implies"): tt1 = eval_tt_f2(PA2.implies)  
  if(f1 == "iff"): tt1 = eval_tt_f2(PA2.iff)
  if(f1 == "npIMPnq"): tt1 = eval_tt_f2(PA2.npIMPnq)
  if(f1 == "nqIMPnp"): tt1 = eval_tt_f2(PA2.nqIMPnp)
  if(f1 == "nand"): tt1 = eval_tt_f2(PA2.nand)
  if(f1 == "nor"): tt1 = eval_tt_f2(PA2.nor)      
  if(f1 == "npANDnq"): tt1 = eval_tt_f2(PA2.npANDnq)
  if(f1 == "npORnq"): tt1 = eval_tt_f2(PA2.npORnq)
  print(tt1)

  # two args
  if(argc>1):
      f2 = args[1]
      print(f2)
      tt2 = []
      if(f2 == "implies"): tt2 = eval_tt_f2(PA2.implies)
      if(f2 == "iff"): tt2 = eval_tt_f2(PA2.iff)
      if(f2 == "npIMPnq"): tt2 = eval_tt_f2(PA2.npIMPnq)
      if(f2 == "nqIMPnp"): tt2 = eval_tt_f2(PA2.nqIMPnp)
      if(f2 == "nand"): tt2 = eval_tt_f2(PA2.nand)
      if(f2 == "nor"): tt2 = eval_tt_f2(PA2.nor)
      if(f2 == "npANDnq"): tt2 = eval_tt_f2(PA2.npANDnq)
      if(f2 == "npORnq"): tt2 = eval_tt_f2(PA2.npORnq)
      print(tt2)
      if equivalent(tt1,tt2):
        print("equivalent!")
      else:
        print("NOT equivalent!")
  print()
  

  
