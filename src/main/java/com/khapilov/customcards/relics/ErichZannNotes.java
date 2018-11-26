package com.khapilov.customcards.relics;

import com.khapilov.customcards.powers.SecretKnowledgePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

/**
 * @author Ross Khapilov
 * @version 1.0 25.11.2018
 */
public class ErichZannNotes extends AbstractRelic {
    public static final String ID = "Erich Zann's Notes";

    public ErichZannNotes() {
        super(ID, "erichZannNotes.png", RelicTier.SPECIAL, LandingSound.MAGICAL);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ErichZannNotes();
    }

    @Override
    public void atBattleStart() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new SecretKnowledgePower(AbstractDungeon.player, 1), 1));
    }
}
