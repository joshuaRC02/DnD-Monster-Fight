from InputMonster import InputMonster
from MeleeWeaponAttack import MeleeWeaponAttack

monster = InputMonster('Homunculus')
atk = set((monster['actions'].keys())).pop()
AdvDis = 'advantage'
AtkStats = dict(monster['actions'][atk])

DefStats = dict(monster['stats'])

print(eval(atk)(AdvDis, AtkStats, DefStats))

