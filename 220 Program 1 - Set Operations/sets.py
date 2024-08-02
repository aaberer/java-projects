def set_new():
    """Return a new set"""
    return []


def set_remove(s, value):
    """Remove the given value from the set s"""
    # perform some type checking to see that the user
    # has provided the right kind of input:
    if type(s) != type([]):
        raise ValueError
    # we can simply use the "remove" method of a list:
    s.remove(value)
    # to be complete before returning we should make sure there are no duplicates - not shown
    return s


def set_union(s1, s2):
    """Return the union of sets s1 and s2 as a list"""
    if type(s1) != type([]) or type(s2) != type([]):
        raise ValueError
    rtr_set = set_new()
    for x in s1:
        if x in rtr_set:
            continue
        else:
            rtr_set.append(x)
    for x in s2:
        if x in rtr_set:
            continue
        if x not in rtr_set:
            rtr_set.append(x)
    return rtr_set


def set_intersection(s1, s2):
    """Return the intersection of sets s1 and s2 as a list"""
    if type(s1) != type([]) or type(s2) != type([]):
        raise ValueError
    rtr_set = set_new()
    for x in s1:
        if x in rtr_set:
            continue
        if x in s2:
            rtr_set.append(x)
    return rtr_set


def set_membership(s, value):
    """Return True if value is in the set s, and False otherwise"""
    if type(s) != type([]):
        raise ValueError
    rtr = False
    if value in s:
        rtr = True
    return rtr


def set_equals(s1, s2):
    """Return True if the sets s1 and s2 have exactly the same elements"""
    if type(s1) != type([]) or type(s2) != type([]):
        raise ValueError
    if (len(s1) == 0) & (len(s2) > 0):
        return False
    if len(s1) == 0 & len(s2) == 0:
        return True
    if s1 in s2:
        if s2 in s1:
            return True
        else:
            return False

    for x in s1:
        if x not in s2:
            return False
    for y in s2:
        if y not in s1:
            return False
    return True


def set_difference(s1, s2):
    """Return the set difference of s1 and s2"""
    if type(s1) != type([]) or type(s2) != type([]):
        raise ValueError
    rtr_set = set_new()
    for x in s1:
        if x in rtr_set:
            continue
        else:
            rtr_set.append(x)
    for x in s2:
        if x in rtr_set:
            rtr_set.remove(x)
    return rtr_set


def is_subset(s1, s2):
    """Return True if s1 is a subset of s2"""
    t1 = 0
    t2 = 0
    count = 0
    if type(s1) != type([]) or type(s2) != type([]):
        raise ValueError
    if len(s1) & len(s2) == 0:
        return True
    if s1 == s2:
        return True
    if s1 in s2:
        return True
    for x in s1:
        t1 += x
    for x in s2:
        t2 += x
    if t1 == t2:
        return True
    while count < len(s1):
        for x in s1:
            for y in s2:
                if x != y:
                    return False
                count += 1
                return True


def is_proper_subset(s1, s2):
    """Return True if s1 is a proper subset of s2"""
    if type(s1) != type([]) or type(s2) != type([]):
        raise ValueError
    t1 = 0
    t2 = 0
    count = 0
    if type(s1) != type([]) or type(s2) != type([]):
        raise ValueError
    if len(s1) > len(s2):
        return False
    if len(s1) == len(s2):
        return False
    if len(s1) == 0:
        return True
    if s1 in s2:
        return True
    for x in s1:
        t1 += x
    for x in s2:
        t2 += x
    if t1 == t2:
        return True
    while count < len(s1):
        for x in s1:
            for y in s2:
                if x != y:
                    return False
                count += 1
                return True
    return False


def run():
    new_set1 = []
    new_set2 = [7, 8, 6, 11, 20, 7, 1]
    print(is_proper_subset(new_set1, new_set2))

if __name__ == '__main__':
    run()


