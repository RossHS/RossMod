package com.khapilov.customcards.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/**
 * @author Ross Khapilov
 * @version 1.0 15.11.2018
 */
public class TypeCasting extends AbstractRelic {
    public static final String ID = "Type Casting";

    public TypeCasting() {
        super(ID, "typeCasting.png", RelicTier.COMMON, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public void onVictory() {
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractPlayer p = AbstractDungeon.player;
        AbstractPower power = p.getPower("Focus");
        if (power != null && p.currentHealth > 0) {
            this.flash();
            p.heal(power.amount);
        }

    }

    @Override
    public AbstractRelic makeCopy() {
        return new TypeCasting();
    }
}
