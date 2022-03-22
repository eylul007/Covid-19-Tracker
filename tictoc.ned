//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License
// along with this program.  If not, see http://www.gnu.org/licenses/.
// 


//simple layer for the Node which is user in our case.
simple tictocNode
{
    gates:
        input portIn; //Input gate to get the information from other node
        output portOut; //Output gate to send information to other node.
}

//
// TODO auto-generated type
//
network tictocNetwork
{
    @display("bgb=256.65,80.04");
    submodules:
        //tictocNode created as tic name for our case it can be userA.
        tic: tictocNode {
            @display("p=31.92,34.44");
        }
        //tictocNode created as toc name for our case it can be userB.
        toc: tictocNode {

            @display("p=210.54001,34.8");
        }
    connections:
        //Connections of the users.
        tic.portOut --> toc.portIn;
        tic.portIn <-- toc.portOut;

}
