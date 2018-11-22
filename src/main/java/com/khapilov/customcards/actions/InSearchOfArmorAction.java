package com.khapilov.customcards.actions;

import com.badlogic.gdx.math.MathUtils;
import com.khapilov.customcards.RossMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/**
 * @author Ross Khapilov
 * @version 1.0 17.10.2018
 */
public class InSearchOfArmorAction extends AbstractGameAction {
    private boolean isUpgraded;

    public InSearchOfArmorAction(boolean upgraded) {
        isUpgraded = upgraded;
        target = AbstractDungeon.player;
        actionType = ActionType.WAIT;
        duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (!isDone) {
            int count = AbstractDungeon.player.hand.size();
            if (count != 0) {
                for (int i = 0; i < count; i++) {
                    AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(getSpecialCard()));
                }
                for (int i = 0; i < count; i++) {
                    AbstractDungeon.actionManager.addToTop(new ExhaustAction(target, target, 1, true, true));
                }
            }
            isDone = true;
        }
    }

    private AbstractCard getSpecialCard() {
        AbstractCard.CardRarity rarity = AbstractCard.CardRarity.COMMON;
        int random = MathUtils.random(20);
        if (random == 20) rarity = AbstractCard.CardRarity.RARE;
        else if (random >= 15) rarity = AbstractCard.CardRarity.UNCOMMON;

        AbstractCard temp;
        do {
            temp = AbstractDungeon.getCard(rarity);
        } while (!(temp.keywords.contains(RossMod.keywordStrings.BLOCK.NAMES[0]) || temp.keywords.contains("block")));
        AbstractCard card = temp.makeCopy();
        if (isUpgraded) card.upgrade();
        return card;
    }
}
