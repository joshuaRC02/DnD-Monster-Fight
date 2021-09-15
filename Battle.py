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
