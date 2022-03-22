/*
 * tictocFunction.cc
 *
 *      Author: BATUHAN
 */

#include<string.h>
#include<omnetpp.h>

using namespace omnetpp;

/**
 * Class for the tictocNode which is actually users for our projects
 * It basically uses cSimpleModule to transfer messages between each node.
 * We declared status and generatedID as a parameters.
 * Status is used for either user is positive or negative corona test. It can be changed as boolean
 * generatedID is used for the privacy issues since we want to keep our users privacy. We used generatedID
 * to send the other users, so that noone will be able to capture the information from the send packet.
 */
class tictocNode : public cSimpleModule {
private:
    int status; //1 or 0
    int generatedID; //Its method will be decided later.
protected:
    void initialize() override;     //Overridden initialize method from cSimpleModule
    void handleMessage(cMessage* msg) override; //Overridden message hanlder method from cSimpleModule
public:
    tictocNode();
};

Define_Module(tictocNode); //registered class with OMNet++


//Generated random status value for both nodes using rand%2
tictocNode::tictocNode() {
    this->status = rand() % 2;
}

void tictocNode :: initialize() {
    printf("status: %d",this->status); //Printing status for debug purposes
    send(new cMessage(""),"portOut");   //send message to the out port, so other node can have the value
}


void tictocNode :: handleMessage(cMessage* msg) {
    //Message created depends on the status.
    if (this->status == 0) {
        msg = new cMessage("No Covid!");
    } else {
        msg = new cMessage("Covid");
    }

    //send to the other node.
    send(msg, "portOut");
}
