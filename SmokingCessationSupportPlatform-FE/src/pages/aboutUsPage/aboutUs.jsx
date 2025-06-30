import React from "react";
import { Affix } from "antd";
import Header from "../../components/header/header";
import Footer from "../../components/footer/footer";
import styles from "./aboutUs.module.css";
import calendarImage from "../../assets/img1.png";
import handsImage from "../../assets/img2.png";

function AboutUs() {
  return (
    <>
      <Affix offsetTop={0}>
        <Header />
      </Affix>

      <div className={styles.container}>
        <h1 className={styles.title}>About QuitIt</h1>
        <img
          src={calendarImage}
          alt="Quit calendar"
          className={styles.headerImage}
        />

        {/* Our Story */}
        <div className={styles.textBlock}>
          <h2 className={styles.subTitle}>Our story</h2>
          <p>
            Behind every promise of “I’ll quit tomorrow” lies a quiet exhaustion
            that few truly understand. No one wants to be addicted. But
            sometimes, quitting is too hard to do alone. We understand that
            feeling — when one part of you wants to stop, but another part still
            holds on.
          </p>
          <p>
            We won’t promise to do it for you. But we will walk with you — day
            by day, step by step. With a clear plan, with a real companion, and
            with understanding — never judgment.
          </p>
          <p>
            That’s why we created QuitIt – so no one has to go through this
            alone again. Because we believe: you deserve a life that is
            healthier, freer, and lighter.
          </p>
        </div>

        {/* How We Do */}
        <div className={styles.sectionReverse}>
          <img src={handsImage} alt="Helping hand" className={styles.image} />
          <div className={styles.textBlock}>
            <h2 className={styles.subTitle}>How we do</h2>
            <p>Helping you overcome addiction — gently and sustainably:</p>
            <ul className={styles.list}>
              <li>A personalized quit plan tailored just for you</li>
              <li>1-on-1 support from a dedicated mentor</li>
              <li>Daily check-ins and gentle reminders to keep you on track</li>
              <li>
                A warm, supportive community where you're always heard and
                understood
              </li>
            </ul>
          </div>
        </div>
      </div>

      <Footer />
    </>
  );
}

export default AboutUs;
