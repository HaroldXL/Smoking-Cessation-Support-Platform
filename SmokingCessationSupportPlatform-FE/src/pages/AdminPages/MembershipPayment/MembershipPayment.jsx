import React, { useState, useEffect } from "react";
import styles from "./MembershipPayment.module.css";
import AdminLayout from "../../../components/layout/AdminLayout.jsx";
import FilterBar from "../../../components/admin/AdminReusableUI/FilterBar";
import BulkActionBar from "../../../components/admin/AdminReusableUI/BulkActionBar";
import ActionDropdown from "../../../components/admin/AdminReusableUI/ActionDropdown";
import ReusableTable from "../../../components/admin/ReusableTable/ReusableTable";
import dayjs from "dayjs";

const MembershipPayment = () => {
  // Mock data cho summary
  const summary = [
    { label: "Total Pro Members", value: 5, icon: "👥" },
    { label: "Expired Members", value: 7, icon: "👥" },
    { label: "Total Revenue", value: "1000", icon: "$" },
  ];

  // Mock data cho bảng payment
  const [payments] = useState([
    {
      id: "VNP001XYZ",
      package: "Premium monthly",
      userId: "U001",
      email: "an.nguyen@example.com",
      amount: "$50",
      date: "2/1/2024",
      status: "ACTIVE",
    },
    {
      id: "VNP001XYZ",
      package: "Premium monthly",
      userId: "U001",
      email: "an.nguyen@example.com",
      amount: "$50",
      date: "2/1/2024",
      status: "ACTIVE",
    },
    {
      id: "VNP001XYZ",
      package: "Premium monthly",
      userId: "U001",
      email: "an.nguyen@example.com",
      amount: "$50",
      date: "2/1/2024",
      status: "EXPIRED",
    },
    {
      id: "VNP001XYZ",
      package: "Premium monthly",
      userId: "U001",
      email: "an.nguyen@example.com",
      amount: "$50",
      date: "2/1/2024",
      status: "EXPIRED",
    },
    {
      id: "VNP001XYZ",
      package: "Premium monthly",
      userId: "U001",
      email: "an.nguyen@example.com",
      amount: "$50",
      date: "2/1/2024",
      status: "ACTIVE",
    },
  ]);

  // Tab state
  const [activeTab, setActiveTab] = useState("history");

  // New state for search and filter
  const [search, setSearch] = useState("");
  const [filterStatus, setFilterStatus] = useState("");
  const [selectedRows, setSelectedRows] = useState([]);
  const [filteredPayments, setFilteredPayments] = useState(payments);

  // Hàm render badge status
  const renderStatus = (status) => {
    if (status === "ACTIVE")
      return (
        <span
          className={`${styles["status-badge"]} ${styles["status-active"]}`}
        >
          ACTIVE
        </span>
      );
    if (status === "EXPIRED")
      return (
        <span
          className={`${styles["status-badge"]} ${styles["status-expired"]}`}
        >
          EXPIRED
        </span>
      );
  };

  useEffect(() => {
    let result = payments.filter((payment) => {
      const matchSearch =
        payment.email.toLowerCase().includes(search.toLowerCase()) ||
        payment.userId.toLowerCase().includes(search.toLowerCase());
      const matchStatus = filterStatus ? payment.status === filterStatus : true;
      return matchSearch && matchStatus;
    });
    setFilteredPayments(result);
  }, [search, filterStatus, payments]);

  const columns = [
    { title: "Transaction ID", dataIndex: "id" },
    { title: "Package name", dataIndex: "package" },
    { title: "User ID", dataIndex: "userId" },
    { title: "Email Users", dataIndex: "email" },
    { title: "Amount", dataIndex: "amount" },
    {
      title: "Payment Date",
      dataIndex: "date",
      render: (value) =>
        dayjs(value, ["D/M/YYYY", "DD/MM/YYYY", "YYYY-MM-DD"]).format(
          "DD/MM/YYYY"
        ),
    },
    { title: "Status", dataIndex: "status", render: renderStatus },
  ];

  return (
    <AdminLayout title="Membership & Payment">
      <div className={styles["membership-payment-page"]}>
        <h2>Membership & Payment</h2>
        <div className={styles["summary-cards-row"]}>
          {summary.map((item, idx) => (
            <div className={styles["summary-card"]} key={idx}>
              <div className={styles["summary-label"]}>{item.label}</div>
              <div className={styles["summary-value"]}>
                {item.icon} {item.value}
              </div>
            </div>
          ))}
        </div>
        <div className={styles["tabs-row"]}>
          <div
            className={`${styles["tab"]} ${
              activeTab === "history" ? styles["tab-active"] : ""
            }`}
            onClick={() => setActiveTab("history")}
          ></div>
          <div
            className={`${styles["tab"]} ${
              activeTab === "plan" ? styles["tab-active"] : ""
            }`}
            onClick={() => setActiveTab("plan")}
          ></div>
        </div>
        <FilterBar
          searchPlaceholder="Search by email, user ID..."
          searchValue={search}
          onSearchChange={(e) => setSearch(e.target.value)}
          filters={[
            {
              placeholder: "Filter status",
              value: filterStatus,
              onChange: setFilterStatus,
              options: [
                { value: "", label: "All status" },
                { value: "ACTIVE", label: "Active" },
                { value: "EXPIRED", label: "Expired" },
              ],
            },
          ]}
        />
        {selectedRows.length > 0 && (
          <BulkActionBar
            selectedCount={selectedRows.length}
            onAction={() => {}}
            actions={[]}
          />
        )}
        <ReusableTable
          columns={columns}
          data={filteredPayments}
          selectedRowKeys={selectedRows}
          onSelectAll={(checked) =>
            setSelectedRows(checked ? filteredPayments.map((p) => p.id) : [])
          }
          onSelectRow={(id, checked) =>
            setSelectedRows((prev) =>
              checked ? [...prev, id] : prev.filter((pid) => pid !== id)
            )
          }
        />
      </div>
    </AdminLayout>
  );
};

export default MembershipPayment;
