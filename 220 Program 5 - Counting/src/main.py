import sys

coins = [1,5,10,25]

def partitions(n,k):
    dp = [[0 for _ in range(k+1)] for _ in range(n+1)]
    for i in range(n+1):
        dp[i][0] = 0
        dp[i][1] = 1
    for j in range(2, k+1):
        dp[0][j] = 0
    for i in range(1, min(n, k)+1):
        dp[i][i] = 1
    for i in range(2, n+1):
        for j in range(2, min(i, k)+1):
            dp[i][j] = dp[i-1][j-1] + j*dp[i-1][j]
    return dp[n][k]
# """
#     pre 0<k<=n, n>0
#     post return the number of ways k partitions 
#           can be formed out of n distinct elements
#    """
   # if k==n or k==1 :
   #  there is only one way to form partitions
   # else :
   # select an element a, and
   #   either
   #     form k partitions with the rest of the elements
   #     and let a join one of these k groups
   #   or
   #     let a form its own partition, and
   #     form k-1 partitions with the rest
    
 
def mkCh(a, c):
    if a == 0:
        return 1
    if a < 0 or c < 0:
        return 0
    return mkCh(a - coins[c], c) + mkCh(a, c - 1)
#  """
#     given coin set {1,5,10,25} count how many ways we can pay amount a,
#     c indicates which coin is considered first.  c starts as the index
#     of the last coin value (len(coins)-1)
#    """


if __name__ == "__main__":
  # partititions
  d = len(sys.argv)>3
  n = int(sys.argv[1])
  k = int(sys.argv[2])
  p = partitions(n,k)
  print("n:",n,"k:",k, "partitions:",p)
  
  # make change
  c = len(coins)-1
  a = 10*n+k
  ways = mkCh(a,c)
  print("amount:", a, "coins:", coins, "ways:", ways)

