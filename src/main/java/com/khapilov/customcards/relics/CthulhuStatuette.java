package com.khapilov.customcards.relics;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/**
 * @author Ross Khapilov
 * @version 1.0 26.11.2018
 */
public class CthulhuStatuette extends AbstractRelic {
    public static final String ID = "Cthulhu Statuette";

    public CthulhuStatuette() {
        super(ID, "cthulhuStatuette.png", RelicTier.SPECIAL, LandingSound.MAGICAL);
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        flash();
        CardCrawlGame.sound.play("VO_SLIMEBOSS_1A");
        AbstractDungeon.actionManager.addToTop(new HealAction(AbstractDungeon.player, AbstractDungeon.player, (int) (m.maxHealth * 0.1)));
//        AbstractDungeon.actionManager.addToTop(new SFXAction("VO_SLIMEBOSS_1A"));
    }

    @Override
    public AbstractRelic makeCopy() {
        return new CthulhuStatuette();
    }
}
