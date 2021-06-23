def MeleeWeaponAttack(AdvDisTemp, AtkStatsTemp, DefStatsTemp):
    # :( Don't like too complex
    from random import randint
    from math import floor
    # making everything usable
    dies = AtkStatsTemp['dmgdie'].split(',')
    dies = [dies[_].split('d') for _ in range(len(dies))]
    types = AtkStatsTemp['type'].split(',')
    types = [types[_].split('+') for _ in range(len(types))]

    # roll for attack and seeing if it hits or is a critical
    AttackRoll = randint(1, 20)
    AttackRoll2 = randint(1, 20)
    if (AttackRoll2 > AttackRoll and AdvDisTemp == 'advantage') or (
            AttackRoll2 < AttackRoll and AdvDisTemp == 'disadvantage'):
        AttackRoll = AttackRoll2
    if AttackRoll == 20:
        hit = 'critically hits'
    elif AttackRoll == 1:
        hit = 'critically misses'
    elif AttackRoll + int(AtkStatsTemp['tohit']) > int(DefStatsTemp['ac']):
        hit = 'misses'
    else:
        hit = 'hits'

    # generating dmg and changing dmg based on variables
    dmg = 0
    for _ in range(len(dies)):
        DmgTemp = 0
        if DefStatsTemp['imm'] in types[_]:
            continue
        elif (not 'halfdmgmiss' in types[_]) and hit in ['critically misses', 'misses']:
            continue

        for __ in range(int(dies[_][0])):
            DmgTemp += randint(1, int(dies[_][1]))
        if 'halfdmgmiss' in types[_] and hit in ['critically misses', 'misses']:
            DmgTemp = floor(DmgTemp / 2)

        if DefStatsTemp['vul'] in types[_]:
            DmgTemp *= 2
        elif DefStatsTemp['res'] in types[_]:
            DmgTemp = floor(DmgTemp / 2)
        dmg += DmgTemp

    if hit == 'critically hits':
        dmg *= 2

    # if there is a condition is a attached it checks if it hits
    condition = 0
    if 'save' in set(AtkStatsTemp.keys()):
        if not (hit in ['critically misses', 'misses']):
            save = DefStatsTemp[AtkStatsTemp['save']]
            if AtkStatsTemp['save'] in DefStatsTemp['prof']:
                save += DefStatsTemp['profbonus']

            if int(AtkStatsTemp['savedc']) > save + randint(1, 20):
                condition = AtkStatsTemp['condition']

    return {'hit': hit, 'dmg': dmg, 'condition': condition}
