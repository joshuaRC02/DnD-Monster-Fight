def InputMonster(monster):
    from math import floor
    from math import ceil
    # opening, reading and then closing the file for the monster
    file = open(monster + '.txt')
    info = file.readlines()
    file.close()

    # making the stats list
    stat = info[0].replace('\n', '').split()
    stats = {}
    for _ in range(len(stat)):
        temp = stat[_].split('=')
        stats[temp[0]] = temp[1]
    stats['hp'] = int(stats['hp'])

    # making the actions list
    actions = {}
    for _ in range(int(info[1].replace('\n', ''))):
        action = info[_ + 2].replace('\n', '').split('_')
        action[1] = action[1].split()
        actions[action[0]] = {}
        for __ in range(len(action[1])):
            temp = action[1][__].split('=')
            actions[action[0]][temp[0]] = temp[1]

    # fixing and establishing some stuff
    fix = ['str', 'dex', 'con', 'int', 'wis', 'cha']
    for _ in range(len(fix)):
        stats[fix[_]] = floor(((int(stats[fix[_]]) - 10) / 2))

    cr = int(round(int(stats['cr'])))
    if cr == 0:
        stats['profbonus'] = 2
    else:
        stats['profbonus'] = ceil(cr / 4) + 1

    # exporting
    combining = {'stats': stats, 'actions': actions}
    return combining
