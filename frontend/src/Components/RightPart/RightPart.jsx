import React from "react";
import SearchIcon from "@mui/icons-material/Search";
import Brightness4Icon from "@mui/icons-material/Brightness4";
import { Button } from "@mui/material";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
import SubscriptionModal from "../SubscriptionModal/SubscriptionModal";

const RightPart = () => {

  const [openSubscriptionModel, setOpenSubscriptionModel] = React.useState(false);
  const handleOpenSubscriptionModel = () => setOpenSubscriptionModel(true);
  const handleCloseSubscriptionModel = () => setOpenSubscriptionModel(false);

  const handleChangeTheme = () => {
    console.log("handle change theme");
  };

  return (
    <div className="py-5 pr-5 sticky top-0">
      <div className="relative flex items-center">
        <input
          type="text"
          className="py-3 rounded-full text-gray-500 w-full pl-12"
        />
        <div className="absolute top-0 left-0 pl-3 pt-3">
          <SearchIcon className="text-gray-500" />
        </div>
        <Brightness4Icon
          className="ml-3 cursor-pointer"
          onClick={handleChangeTheme}
        />
      </div>
      <section className="my-5 ml-3 pr-5">
        <h1 className="text-xl font-bold">Get verified</h1>
        <h1 className="font-bold my-2">Subscribe to unlock new Features</h1>
        <Button
          variant="contained"
          sx={{ padding: "10px", paddingX: "20px", borderRadius: "25px" }}
          onClick={handleOpenSubscriptionModel}
        >
          Get Verified
        </Button>
      </section>
      <section className="mt-7 ml-3 space-y-5 flex justify-between">
        <div className="items-center justify-normal">
          <h1 className="font-bold text-xl py-1">What's happening</h1>
          <div className="flex items-center justify-start my-5">
            <div>
              <p className="text-m">Politics · Trending</p>
              <p className="font-bold">#BoycottMaldives</p>
              <p className="text-m">Trending with #Lakshadweep</p>
            </div>
          </div>

          <div className="flex items-center justify-start my-5">
            <div>
              <p className="text-m">Sports · Trending</p>
              <p className="font-bold">#INDvsAFG</p>
              <p className="text-m">Trending with #ISRO, #AdityaL1</p>
            </div>
          </div>

          <div className="flex items-center justify-start my-5">
            <div>
              <p className="text-m">Trending in India</p>
              <p className="font-bold">Indian Ocean</p>
              <p className="text-m">Trending with Sri Lanka</p>
            </div>
          </div>

          <div className="flex items-center justify-start my-5">
            <div>
              <p className="text-m">Technology · Trending</p>
              <p className="font-bold">JavaScript</p>
              <p className="text-m">8,965 posts</p>
            </div>
          </div>
        </div>
        <div className="flex flex-col space-y-7 pr-5 pt-10">
          <div className="pb-10">
            <MoreHorizIcon
              sx={{
                color: "gray",
              }}
            />
          </div>
          <div className="pb-10">
            <MoreHorizIcon
              sx={{
                color: "gray",
              }}
            />
          </div>
          <div className="pb-10">
            <MoreHorizIcon
              sx={{
                color: "gray",
              }}
            />
          </div>
          <div className="pb-10">
            <MoreHorizIcon
              sx={{
                color: "gray",
              }}
            />
          </div>
        </div>
      </section>
      <section>
        <SubscriptionModal open={openSubscriptionModel} handleClose={handleCloseSubscriptionModel}/>
      </section>
    </div>
  );
};

export default RightPart;
