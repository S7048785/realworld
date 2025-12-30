import {useLocation, useOutlet} from "react-router-dom";
import { motion, AnimatePresence } from "motion/react";

function AnimatedOutlet() {
  const location = useLocation();
  const outlet = useOutlet();

  return (
    <div className="relative">
      <AnimatePresence mode="wait">
        <motion.div
          key={location.pathname}
          initial={{ y: 2, opacity: 0, filter : "blur(2px)" }}
          animate={{ y: 0, opacity: 1, filter : "blur(0px)" }}
          exit={{ y: 2, opacity: 0, filter : "blur(2px)" }}
          className="absolute inset-0 w-full "
        >
          {outlet}
        </motion.div>
      </AnimatePresence>
    </div>
  );
}

export default AnimatedOutlet;