from InputMonster import InputMonster
from MeleeWeaponAttack import MeleeWeaponAttack
from random import randint
from math import floor

# list to help with choosing the monsters
f = open('Monster List.txt')
fList = f.read()
print(fList)
fList = set(fList.split('\n'))
f.close()

# user input for which monsters are fighting
monsters = {}

# monster1 = input('First Monster:')
monster1 = fList.pop()
monsters[monster1] = InputMonster(monster1)

# monster2 = input('Second Monster:')
monster2 = fList.pop()
monsters[monster2] = InputMonster(monster2)

# getting a list of all unique actions
UniqueAbilty = set(list(monsters[monster1]['actions'].keys()) + list(monsters[monster2]['actions'].keys()))

# importing all of the unique actions
abilities = {}
n = int(len(UniqueAbilty))
for _ in range(n):
    temp = UniqueAbilty.pop()
    exec('from ' + temp + ' import ' + temp)

# who goes first
if monsters[monster1]['stats']['dex'] > monsters[monster2]['stats']['dex']:
    first = monster1
    second = monster2
else:
    first = monster2
    second = monster1

# setting up turn order and initializing some variables
TurnOrder = [first, second]
monsters[first]['advantage'] = '0'
monsters[second]['advantage'] = '0'
TurnAdvDis = '0'

# battle
while (monsters[monster1]['stats']['hp'] > 0) and (monsters[monster2]['stats']['hp'] > 0):
    # getting the monster who's attacking (first) and defending (second)
    second = TurnOrder.pop()
    first = TurnOrder.pop()

    TurnOrder.append(second)
    TurnOrder.append(first)

    AttackingActions = monsters[first]['actions']

    TurnAdvDis = monsters[first]['advantage']
    # getting which atk is going to be used
    atk = set((AttackingActions.keys())).pop()

    # easy stats
    AtkStats = AttackingActions[atk]
    DefStats = dict(monsters[second]['stats'])

    # get results and prints in a niceish way
    result = eval(atk)(TurnAdvDis, AtkStats, DefStats)

    # deals dmg and making sure that monster's hp don't go negative in
    monsters[second]['stats']['hp'] -= result['dmg']
    if monsters[second]['stats']['hp'] < 0:
        monsters[second]['stats']['hp'] = 0

    # printing the info for the round
    print('The ' + first + ' attacks the ' + second + '.')
    print('The ' + first + ' ' + result['hit'] + ' the ' + second + ' and deals ' + str(result['dmg']) + ' dmg.')
    print('The ' + second + " has " + str(monsters[second]['stats']['hp']) + ' hp.', '\n')
