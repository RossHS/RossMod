package com.khapilov.customcards.cards.curses;

import basemod.abstracts.CustomCard;
import com.khapilov.customcards.actions.ExhaustCardNightmareAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

/**
 * @author Ross Khapilov
 * @version 1.0 26.11.2018
 */
public class Nightmare extends CustomCard {
    public static final String ID = "Nightmare";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = -2;

    public Nightmare() {
        super(ID, NAME, "cards/nightmare.png", COST, DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
    }

    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
        CardCrawlGame.sound.play("VO_NEMESIS_1C");
        AbstractDungeon.actionManager.addToBottom(new ExhaustCardNightmareAction(AbstractDungeon.player, AbstractDungeon.player, this));
    }

    @Override
    public void upgrade() {
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hasRelic("Blue Candle")) useBlueCandle(p);
        else AbstractDungeon.actionManager.addToBottom(new UseCardAction(this));

    }

    @Override
    public AbstractCard makeCopy() {
        return new Nightmare();
    }
}
