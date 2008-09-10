package org.jpowder.fileCabinet;
// #[regen=yes,id=DCE.D5369783-12D5-BB31-4A7C-D338EA35C355]
// </editor-fold> 
public interface Subject {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.AD4E750D-D75C-B74D-9743-C0A1BC553F4C]
    // </editor-fold> 
    public void registerObserver (PowderFileObserver o);

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.0A369B03-F208-5DAD-B6A4-271DC36A4CC7]
    // </editor-fold> 
    public void removeObserver (PowderFileObserver o);

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3D23A62E-6283-59BC-F152-31C162D7D885]
    // </editor-fold> 
    public void notifyObservers ();

}

