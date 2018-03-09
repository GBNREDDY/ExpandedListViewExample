package com.support.expandedlistviewexample

/**
 * Created by admin on 3/9/2018.
 */

object StaticData {
    fun isPresent(mselitem: ArrayList<SelModel>, s: SelModel): Boolean {
        mselitem.indices
                .map { mselitem[it] }
                .forEach{
            if (it.item.equals(s.item) && it.parentpos == s.parentpos && it.childpos == s.childpos){
                return true
            }
        }
        return false
    }

    fun remove(mselitem: ArrayList<SelModel>, s: SelModel):SelModel? {
        mselitem.indices
                .map { mselitem[it] }
                .forEach{
                    if (it.item.equals(s.item) && it.parentpos == s.parentpos && it.childpos == s.childpos){
                        return it
                    }
                }
        return null
    }
}
