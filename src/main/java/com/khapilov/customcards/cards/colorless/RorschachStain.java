package com.khapilov.customcards.cards.colorless;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.QueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;
import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * @author Ross Khapilov
 * @version 1.0 25.10.2018
 */
abstract public class RorschachStain extends CustomCard {
    private static final int COST = -2;
    private static final int CARDS_TO_DRAW = 1;

    public RorschachStain(String id, String name, String img, String rawDescription, CardType type, CardTarget target) {
        super(id, name, img, COST, rawDescription, type, CardColor.COLORLESS, CardRarity.SPECIAL, target);
        exhaust = true;

        setUniqueProperty();
        if (AbstractDungeon.player != null && AbstractDungeon.player.hasPower("Eye-Opener"))
            upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        targetAngle = 0;
        addEffect();
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, CARDS_TO_DRAW));
        if (AbstractDungeon.getRandomMonster().getClass() == TimeEater.class) {
            AbstractPower power = AbstractDungeon.getRandomMonster().getPower("Time Warp");
            power.amount--;
        }
    }

    public void triggerWhenDrawn() {
        AbstractDungeon.actionManager.addToTop(new QueueCardAction(this, AbstractDungeon.getRandomMonster()));
    }

    @Override
    abstract public AbstractCard makeCopy();

    abstract protected void addEffect();

    abstract protected void setUniqueProperty();
}