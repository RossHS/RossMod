package com.khapilov.customcards.actions;

import com.badlogic.gdx.math.MathUtils;
import com.khapilov.customcards.RossMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author Ross Khapilov
 * @version 1.0 17.10.2018
 */
public class MagicArmorAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private AbstractCard.CardType cardType = AbstractCard.CardType.SKILL;
    private static final float PAD_X = 40.0F * Settings.scale;
    private static final float CARD_TARGET_Y = (float) Settings.HEIGHT * 0.45F;

    public MagicArmorAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }


    private void setCards(ArrayList<AbstractCard> cards) {
        cards.clear();
        AbstractCard.CardRarity rarity = AbstractCard.CardRarity.COMMON;
        boolean needRarityCorrect = true;
        while (cards.size() != 3) {
            boolean dupe = false;
            if (needRarityCorrect) {
                int random = MathUtils.random(20);
                rarity = AbstractCard.CardRarity.COMMON;
                if (random == 20) rarity = AbstractCard.CardRarity.RARE;
                else if (random >= 15) rarity = AbstractCard.CardRarity.UNCOMMON;
                needRarityCorrect = false;
            }
            AbstractCard card = AbstractDungeon.getCard(rarity);
            if (!(card.keywords.contains(RossMod.keywordStrings.BLOCK.NAMES[0]) || card.keywords.contains("block"))) continue;
            for (AbstractCard c : cards) {
                if (c.cardID.equals(card.cardID)) {
                    dupe = true;
                    break;
                }
            }
            if (!dupe) {
                cards.add(card.makeCopy());
                needRarityCorrect = true;
            }
        }

        cards.get(0).target_x = (float) Settings.WIDTH / 2.0F - AbstractCard.IMG_WIDTH - PAD_X;
        cards.get(1).target_x = (float) Settings.WIDTH / 2.0F;
        cards.get(2).target_x = (float) Settings.WIDTH / 2.0F + AbstractCard.IMG_WIDTH + PAD_X;
        cards.get(0).target_y = CARD_TARGET_Y;
        cards.get(1).target_y = CARD_TARGET_Y;
        cards.get(2).target_y = CARD_TARGET_Y;

        AbstractCard c;
        for (Iterator<AbstractCard> iter = cards.iterator(); iter.hasNext(); c.current_y = CARD_TARGET_Y) {
            c = iter.next();
            c.drawScale = 0.75F;
            c.targetDrawScale = 0.75F;
            c.current_x = (float) Settings.WIDTH / 2.0F;
        }
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.discoveryOpen(this.cardType);
            ArrayList<AbstractCard> cards = AbstractDungeon.cardRewardScreen.rewardGroup;
            setCards(cards);
            this.tickDuration();
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                    disCard.current_x = -1000.0F * Settings.scale;
                    if (AbstractDungeon.player.hand.size() < 10) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                    } else {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, (float) Settings.WIDTH / 2.0F, (float) Settings.HEIGHT / 2.0F));
                    }
                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }
                this.retrieveCard = true;
            }
            this.tickDuration();
        }
    }

}
