package com.khapilov.customcards.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

/**
 * @author Ross Khapilov
 * @version 1.0 20.10.2018
 */
public class ObtainGoldAction extends AbstractGameAction {
    private int amount;
    private float x;
    private float y;

    public ObtainGoldAction(AbstractCreature source, float x, float y, int amount) {
        actionType = ActionType.SPECIAL;
        duration = Settings.ACTION_DUR_XFAST;
        this.amount = amount;
        this.source = source;
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            if (amount >= 0) {
                for (int i = 0; i < amount; ++i)
                    AbstractDungeon.effectList.add(new GainPennyEffect(this.source, x, y, this.source.hb.cX, this.source.hb.cY, true));
                AbstractDungeon.player.gainGold(amount);
            } else
                AbstractDungeon.player.loseGold(-amount);
        }
        this.tickDuration();
    }
}
