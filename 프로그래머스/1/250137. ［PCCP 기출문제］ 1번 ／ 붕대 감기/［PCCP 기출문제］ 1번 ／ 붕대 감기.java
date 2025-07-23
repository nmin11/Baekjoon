class Solution {
    private int castingTime, healPerSec, bonusHeal, maxHp;
    
    public int solution(int[] bandage, int health, int[][] attacks) {
        this.castingTime = bandage[0];
        this.healPerSec = bandage[1];
        this.bonusHeal = bandage[2];
        this.maxHp = health;
        
        int curTime = 0;
        int maxTime = attacks[attacks.length - 1][0];
        
        for (int i = 0; i < attacks.length; i++) {
            int attackTime = attacks[i][0];
            int healDuration = attackTime - curTime - 1;
            if (healDuration > 0) {
                int healAmount = healPerSec * healDuration;
                int bonusHealCount = healDuration / castingTime;
                healAmount += bonusHeal * bonusHealCount;
                health = Math.min(health + healAmount, maxHp);
            }
            
            health -= attacks[i][1];
            if (health <= 0) return -1;
            curTime = attackTime;
        }
        
        return health;
    }
}