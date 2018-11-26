package com.khapilov.customcards.powers;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * @author Ross Khapilov
 * @version 1.0 26.11.2018
 */
public class SecretKnowledgePower extends AbstractPower {
    public static final String POWER_ID = "Secret Knowledge";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SecretKnowledgePower(AbstractCreature owner, int val) {
        name = NAME;
        ID = POWER_ID;
        this.owner = owner;
        this.amount = val;
        description = DESCRIPTIONS[0];
        img = ImageMaster.loadImage("powers/secretKnowledge.png");
    }

    @Override
    public int onLoseHp(int damageAmount) {
        if (damageAmount > 0) {
            AbstractDungeon.actionManager.addToTop(new ReducePowerAction(owner, owner, ID, 1));
        }
        AbstractDungeon.actionManager.addToTop(new HealAction(owner, owner, damageAmount * 2));
        return 0;
    }

    @Override
    public void updateDescription() {
        if (amount <= 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
