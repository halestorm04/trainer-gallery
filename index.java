import React, { useState, useMemo } from 'react';
import { Search, Filter, Image as ImageIcon } from 'lucide-react';

// --- STEP 1: PUT YOUR IMAGE LINK HERE ---
// You can use a URL (https://...) or a local import if using a bundler.
const DISPLAY_IMAGE = "https://images.unsplash.com/photo-1613771404798-606471803f75?q=80&w=600&auto=format&fit=crop"; 

const cardData = [
  { name: "Canari", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Candela", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Candice", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cara Liss", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Caretaker", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Carmine", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cassiopeia", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cassius", details: "XY 115/146", type: "Supporter" },
  { name: "Castaway", details: "EX Crystal Guardians 72/100", type: "Supporter" },
  { name: "Cedric Juniper", details: "Legendary Treasures 110/113", type: "Supporter" },
  { name: "Celio's Network", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Champions Festival", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Channeler", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Charon's Conspiracy", details: "Rising Rivals RT6", type: "Supporter" },
  { name: "Cheerleader's Cheer", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cheren", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cheren's Care", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cheryl", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Chili & Cilan & Cress", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Choy", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cilan", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Ciphermaniac's Codebreaking", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Clavell", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Clay", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Clemont", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Clemont's Quick Wit", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Clive", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Coach Trainer", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Colress", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Colress' Experiment", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Colress's Tenacity", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cook", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Copycat", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Crasher Wake", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Crispin", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cyllene", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cynthia", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cynthia & Caitlin", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cynthia's Ambition", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cynthia's Feelings", details: "Legends Awakened 131/146", type: "Supporter" },
  { name: "Cynthia's Guidance", details: "Supreme Victors 136/147", type: "Supporter" },
  { name: "Cyrano", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cyrus", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cyrus's Conspiracy", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Cyrus's Initiative", details: "Supreme Victors 137/147", type: "Supporter" },
  { name: "Daisy's Help", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Dan", details: "Rebel Clash 158/192", type: "Supporter" },
  { name: "Dana", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Dancer", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Dawn", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Delinquent", details: "BREAKPoint 98/122", type: "Supporter" },
  { name: "Dendra", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Department Store Girl", details: "Arceus 85/99", type: "Supporter" },
  { name: "Desert Shaman", details: "Skyridge 123/144", type: "Supporter" },
  { name: "Diantha", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Digging Duo", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Doctor", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Drasna", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Drayton", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Elesa", details: "Legendary Treasures RC20/RC25", type: "Supporter" },
  { name: "Elesa's Sparkle", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Emcee's Chatter", details: "Unleashed 73/95", type: "Supporter" },
  { name: "Emcee's Hype", details: "Destined Rivals 220/182", type: "Supporter" },
  { name: "Emcee's Hyper", details: "Destined Rivals 163/182", type: "Supporter" },
  { name: "Engineer's Adjustments", details: "Unleashed 75/95", type: "Supporter" },
  { name: "Eri", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Erika", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Erika's Hospitality", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Erika's Invitation", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Ethan's Adventure", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Evelyn", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Explorer's Guidance", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Falkner", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Fantina", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Felicity's Drawing", details: "Great Encounters 98/106", type: "Supporter" },
  { name: "Fennel", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Fennel's Assistance", details: "Everyones Exciting Battle 47/47", type: "Supporter" },
  { name: "Fieldworker", details: "EX Legend Maker 73/92", type: "Supporter" },
  { name: "Firebreather", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Fisherman", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Flannery", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Flint's Willpower", details: "Rising Rivals 91/111", type: "Supporter" },
  { name: "Flower Shop Lady", details: "Undaunted 74/90", type: "Supporter" },
  { name: "Forest Guardian", details: "Aquapolis 123/147", type: "Supporter" },
  { name: "Fossil Excavator", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Fossil Researcher", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Friends in Galar", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Friends in Hisui", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Friends in Paldea", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Friends in Sinnoh", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Galar Friends", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Gardenia", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Geeta", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Ghetsis", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Giovanni's Charisma", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Gladion", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Gloria", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Green's Exploration", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Guzma", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Hala", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Hassel", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Hau", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Hex Maniac", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Holon Adventurer", details: "EX Holon Phantoms 85/110", type: "Supporter" },
  { name: "Holon Farmer", details: "EX Delta Species 91/113", type: "Supporter" },
  { name: "Holon Lass", details: "EX Delta Species 92/113", type: "Supporter" },
  { name: "Honey", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Hop", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Iono", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Irida", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Iris", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Jacq", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Janine", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Jasmine", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Jessie and James", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Judge", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Kabu", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Karen", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Klara", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Korrina", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Lacey", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Lady", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Lana", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Lance", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Larry", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Leon", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Lillie", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Looker", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Lusamine", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Lysandre", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Mallow", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Marnie", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Melony", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Miriam", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Misty", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Morty", details: "Multiple. Click for details", type: "Trainer" },
  { name: "N", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Nemona", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Nessa", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Olivia", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Penny", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Peony", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Phoebe", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Professor Birch", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Professor Elm", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Professor Juniper", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Professor Oak", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Professor Rowan", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Professor Sycamore", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Raihan", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Rose", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Roxanne", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Serena", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Shauna", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Skyla", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Sonia", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Steven", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Volkner", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Wally", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Whitney", details: "Multiple. Click for details", type: "Trainer" },
  { name: "Zinnia", details: "Multiple. Click for details", type: "Trainer" },
];

export default function App() {
  const [searchTerm, setSearchTerm] = useState('');
  const [filterType, setFilterType] = useState('All');

  const filteredCards = useMemo(() => {
    return cardData.filter(card => {
      const matchesSearch = card.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
                            card.details.toLowerCase().includes(searchTerm.toLowerCase());
      const matchesType = filterType === 'All' || card.type === filterType;
      return matchesSearch && matchesType;
    });
  }, [searchTerm, filterType]);

  return (
    <div className="min-h-screen bg-slate-50 p-6 md:p-10 font-sans">
      <div className="max-w-7xl mx-auto">
        {/* Header */}
        <div className="mb-10 text-center">
          <h1 className="text-4xl font-bold text-slate-900 mb-2">TCG Supporter Gallery</h1>
          <p className="text-slate-600">Explore and view artwork for {cardData.length} extracted cards</p>
        </div>

        {/* Controls */}
        <div className="bg-white rounded-2xl shadow-sm border border-slate-200 p-4 mb-8 flex flex-col md:flex-row gap-4">
          <div className="relative flex-grow">
            <Search className="absolute left-3 top-1/2 -translate-y-1/2 text-slate-400 w-5 h-5" />
            <input
              type="text"
              placeholder="Search by name or set..."
              className="w-full pl-10 pr-4 py-2 bg-slate-50 border-none rounded-xl focus:ring-2 focus:ring-blue-500 transition-all outline-none"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>
          <div className="flex items-center gap-2">
            <Filter className="text-slate-400 w-5 h-5" />
            <select
              className="bg-slate-50 border-none rounded-xl px-4 py-2 outline-none focus:ring-2 focus:ring-blue-500"
              value={filterType}
              onChange={(e) => setFilterType(e.target.value)}
            >
              <option value="All">All Types</option>
              <option value="Supporter">Supporter</option>
              <option value="Trainer">Trainer</option>
            </select>
          </div>
        </div>

        {/* Grid */}
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-6">
          {filteredCards.map((card, index) => (
            <div 
              key={`${card.name}-${index}`}
              className="group bg-white rounded-2xl border border-slate-200 overflow-hidden hover:shadow-xl transition-all hover:-translate-y-1"
            >
              {/* --- STEP 2: IMAGE DISPLAY --- */}
              <div className="aspect-[3/4] bg-slate-100 flex items-center justify-center relative overflow-hidden">
                <img 
                    src={DISPLAY_IMAGE} 
                    alt={card.name}
                    className="w-full h-full object-cover"
                />
              </div>
              
              <div className="p-5">
                <div className="flex justify-between items-start mb-2">
                  <h3 className="font-bold text-slate-900 leading-tight">{card.name}</h3>
                  <span className={`text-[10px] px-2 py-0.5 rounded-full font-bold uppercase tracking-wider ${
                    card.type === 'Supporter' ? 'bg-orange-100 text-orange-700' : 'bg-blue-100 text-blue-700'
                  }`}>
                    {card.type}
                  </span>
                </div>
                <p className="text-sm text-slate-500 italic mb-4">{card.details}</p>
              </div>
            </div>
          ))}
        </div>

        {filteredCards.length === 0 && (
          <div className="text-center py-20 bg-white rounded-3xl border border-dashed border-slate-300">
            <ImageIcon className="mx-auto text-slate-200 mb-4" size={64} />
            <h2 className="text-xl font-semibold text-slate-400">No cards found matching your criteria</h2>
          </div>
        )}
      </div>
    </div>
  );
}
